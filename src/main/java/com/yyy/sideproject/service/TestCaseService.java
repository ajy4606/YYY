package com.yyy.sideproject.service;

import com.yyy.sideproject.domain.TestCase;
import com.yyy.sideproject.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;

    @Transactional
    public void uploadExcelFile(Long issueId, MultipartFile file) throws Exception {
        // DB에 한 번에 밀어 넣을 TC들을 담아둘 리스트 바구니
        List<TestCase> testCases = new ArrayList<>();

        // 1. 사용자가 올린 엑셀 파일(MultipartFile) 열기
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
             
            // 2. 엑셀의 첫 번째 시트(Sheet) 가져오기
            Sheet sheet = workbook.getSheetAt(0);
            
            // 3. 0번째 줄은 제목(헤더)이므로 건너뛰고, 1번째 줄(Row)부터 마지막 줄까지 반복!
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue; // 빈 줄이면 건너뜀

                // 엑셀 셀(Cell) 데이터 읽기
                // A열(0번): 카테고리, B열(1번): 시나리오, C열(2번): 기대결과
                String category = getCellValue(row.getCell(0));
                String description = getCellValue(row.getCell(1));
                String expected = getCellValue(row.getCell(2));

                // 방어 로직: '시나리오(필수)' 칸이 비어있다면 무효한 데이터로 간주하고 패스!
                if (description == null || description.trim().isEmpty()) {
                    continue;
                }

                // 4. 자바 Entity 객체(TestCase) 생성 후 데이터 채워넣기
                TestCase tc = new TestCase();
                tc.setIssueId(issueId);       // 이 TC가 어떤 과제 소속인지 기록
                tc.setTcCategory(category);
                tc.setTcDescription(description);
                tc.setExpectedResult(expected);
                
                // 바구니에 담기
                testCases.add(tc);
            }
        }
        
        // 5. 바구니에 담긴 수십/수백 개의 TC를 DB에 일괄 저장 (Insert 쿼리)
        testCaseRepository.saveAll(testCases);
    }

    // [헬퍼 메서드] 엑셀의 칸(Cell) 형식이 숫자든 문자든 에러 없이 문자열로 안전하게 꺼내주는 마법의 메서드
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: 
                // 소수점 1.0 같은 형태로 나오는 것을 방지
                return String.valueOf((int) cell.getNumericCellValue()); 
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }
}