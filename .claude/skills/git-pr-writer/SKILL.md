---
name: git-pr-writer
description: FLIP-Backend GitHub PR 설명을 작성할 때 사용. 사용자가 "PR 써줘/만들어줘", "PR 설명 작성해줘" 같은 요청을 하면 이 스킬을 따라 프로젝트 컨벤션을 지키면서 간결하게 작성한다.
---

## 반드시 따를 템플릿/컨벤션
FLIP-Backend엔 `.github/pull_request_template.md` 파일이 아직 없음 — 기존 PR들이 자유 형식으로 정착시킨 아래 섹션 구조를 그대로 사용 (섹션 새로 만들거나 빼지 않기):

```markdown
## 변경 사항
- (엔드포인트/기능 단위로 무엇이 추가·수정됐는지)

## 설계
- (왜 이렇게 했는지, 검증 규칙, 에러 처리, 트레이드오프 등)

## 검증
- (빌드/컴파일/테스트 확인 결과)
```

- 이전 PR에 stacked(의존)된 PR이면 본문 맨 위에 인용구(`>`)로 베이스 PR과 관계 명시
- 제목은 `<type>: <한 줄 요약>` (`feat`/`fix`/`perf`/`refactor` 등)
- 본문 맨 끝에 `🤖 Generated with [Claude Code](https://claude.com/claude-code)` 한 줄 추가
- `gh pr create --repo UNITON-3DAYS/FLIP-Backend --title "..." --body "..."`로 생성

## 작성 톤 (중요)
- 각 섹션은 짧은 불릿 위주. 완전한 문장/서술형 문단 금지
- "~했습니다", "~를 진행하였습니다" 대신 명사형/개조식으로 ("~추가", "~분리", "~검증")
- "설계" 섹션은 왜(why)가 중요하므로 판단 근거나 트레이드오프를 한 줄씩 붙임 — 나머지 섹션은 항목만
- 새 엔드포인트를 추가하는 PR이면 "변경 사항"에 HTTP 메서드+경로를 그대로 나열
- 해당 없는 섹션은 "(해당 없음)"으로 짧게 표시하고 섹션 자체는 지우지 않기

## PR 생성 전 확인
- 제목·본문 초안을 사용자에게 먼저 보여주고 컨펌 받은 뒤 `gh pr create` 실행할 것 (임의로 바로 생성하지 않기)

## 예시 (few-shot)
실제 작성 예시는 [examples.md](examples.md) 참고 — 반드시 이 정도 밀도로 작성할 것
