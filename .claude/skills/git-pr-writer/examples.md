# 실제 작성 예시 (few-shot)

이 프로젝트 GitHub 이력에서 그대로 가져온 예시. 이 정도 밀도로 작성할 것.

## PR 예시 (PR #1) — 신규 CRUD API 추가

```markdown
## 변경 사항
- `POST /api/worksheets` — 문제지 생성 (title, source)
- `PATCH /api/worksheets/{worksheetId}` — 타이틀·유형 수정
- `DELETE /api/worksheets/{worksheetId}` — 삭제 (hard delete)
- 기존 `GET /api/worksheets` 목록 조회는 그대로 유지

## 설계
- `Worksheet`에 `(title, source)` 유니크 제약 추가
- 직접 생성 시 중복이면 409 `WORKSHEET_ALREADY_EXISTS` (`findByTitleAndSource`로 확인), 수정 시에도 자기 자신 제외 중복 검사
- 채점 기록·문항이 참조 중이면 삭제 차단 409 `WORKSHEET_HAS_REFERENCES` (GradingRecord/Question `existsByWorksheetId`)
- `WorksheetReader.getById` 단건 조회 추가 (없으면 `WORKSHEET_NOT_FOUND`)
- 채점 플로우용 `WorksheetCreator.findOrCreate`는 변경 없이 유지
- 인증 없음, hard delete

## 검증
- `./gradlew compileKotlin compileTestKotlin` 성공

🤖 Generated with [Claude Code](https://claude.com/claude-code)
```

## PR 예시 (PR #3) — 부수 효과(사이드이펙트) 있는 삭제 로직

```markdown
## 변경 사항
- `POST /api/students` 학생 생성 (name/grade/password/schoolId 필수, 201 반환)
- `PATCH /api/students/{studentId}` 이름/학년/학교/비밀번호 수정
- `DELETE /api/students/{studentId}` 학생 삭제 (하드 삭제, 204 반환)
- BCrypt 비밀번호 해싱: `spring-security-crypto` 의존성 추가 + `PasswordEncoderConfig` 빈 정의 (security starter/필터 미사용)
- 생성/수정 시 비밀번호 BCrypt 해싱, 응답에 비밀번호 미노출 (`StudentResponse` 재사용)
- `schoolId`를 `SchoolReader.getById`로 검증 후 연관 (`Student.school`을 `var`로 변경)
- `StudentCreator` / `StudentValidator` / `StudentDeleter` 컴포넌트 추가
- 채점 기록 존재 시 삭제 차단 (409 `STUDENT_HAS_GRADING_RECORDS`, `GradingRecordRepository.existsByStudentId`)
- `ErrorCode`에 `STUDENT_HAS_GRADING_RECORDS` 추가

🤖 Generated with [Claude Code](https://claude.com/claude-code)
```

- "설계" 섹션이 필요 없을 만큼 자명한 변경이면 생략하고 "변경 사항"만 쓰기도 함 (위 예시)

## Stacked PR (이전 PR에 의존) 예시 (PR #4)

```markdown
> Stacked on top of `feature/akr-25-worksheet-crud` (문제지 CRUD PR). 앞선 PR 머지 후 base가 자동 retarget됩니다.

## 변경 사항
문제지 하위 중첩 리소스로 문항(Question) CRUD 추가:
- `POST /api/worksheets/{worksheetId}/questions` — 생성 (questionNumber, page, correctAnswer, type?, numChoices?)
- `GET /api/worksheets/{worksheetId}/questions` — 목록 조회
- `PATCH /api/worksheets/{worksheetId}/questions/{questionId}` — 수정
- `DELETE /api/worksheets/{worksheetId}/questions/{questionId}` — 삭제 (hard delete)

## 설계
- 미사용 상태였던 `QuestionRepository`를 활용 (조회·존재 검사 메서드 추가)
- `QuestionService` + `QuestionReader`/`QuestionCreator`/`QuestionValidator` 신설
- `worksheetId`는 `WorksheetReader.getById`로 검증, 문항이 해당 문제지 소속인지 검증 (아니면 `QUESTION_NOT_FOUND`)
- `(worksheet, page, questionNumber)` 애플리케이션 레벨 중복 검사 → 409 `QUESTION_ALREADY_EXISTS` (DB 유니크 제약은 기존 문항 데이터 영향 우려로 생략)
- 채점/FLIP-AI 경로에는 연결하지 않음 (데이터 관리 CRUD 한정)

## 검증
- `./gradlew compileKotlin compileTestKotlin` 성공

🤖 Generated with [Claude Code](https://claude.com/claude-code)
```

## 신선한 예시가 더 필요하면
이 파일이 오래됐다 싶으면 직접 최근 이력을 확인해서 참고할 것:
```bash
gh pr list --repo UNITON-3DAYS/FLIP-Backend --state all --limit 10 --json number,title,body
```
