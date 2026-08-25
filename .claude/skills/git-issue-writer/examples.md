# 실제 작성 예시 (few-shot)

이 프로젝트 GitHub 이력에서 그대로 가져온 예시. 이 정도 밀도로 작성할 것.

## 이슈 예시 (issue #5)

```markdown
## 📝 개요
- `GradingRecordService.uploadImage`가 GCS 읽기 + AI 서버 호출까지 하나의 트랜잭션 안에서 처리돼, DB 커넥션이 외부 호출 시간만큼 점유됨
- 동시 요청이 늘어나면 커넥션 풀 고갈 위험

## ✅ To-Do
- [ ] GradingImage 저장(짧은 트랜잭션)과 GCS/AI 호출(트랜잭션 밖)을 분리
- [ ] GradingResult 저장도 별도 트랜잭션으로 재구성

## 👀 ETC
- 단일 요청 응답 속도보다 동시성/처리량 개선이 목적
```

## 이슈 예시 (issue #7, ETC에 트레이드오프 명시)

```markdown
## 📝 개요
- 채점 이미지 업로드가 `imageUrl` 참조 방식이라, 서버가 GCS에 이미 올라간 이미지를 다시 읽어와 base64로 변환하는 왕복이 발생
- 이미지 용량에 따라 수십~수백 ms 지연 추정

## ✅ To-Do
- [ ] `/grading-records/{id}/images`를 multipart 직접 수신 방식으로 변경 검토
- [ ] GCS 저장(백업용)을 응답과 분리해 비동기 처리
- [ ] 프론트 API 계약 변경 필요 — 사전 협의

## 👀 ETC
- API 계약 변경이 필요해 셋 중 적용 우선순위는 가장 낮음
```

간단한 이슈는 To-Do 한두 줄로 끝내기도 함:

```markdown
## 📝 개요
- `GradingResultRecorder`가 문항 하나마다 조회 1번 + 저장 1번을 반복해, 페이지당 최대 12~14개 쿼리 발생

## ✅ To-Do
- [ ] 해당 페이지의 기존 `GradingResult`를 한 번에 조회
- [ ] 메모리에서 insert/update 대상 구분 후 배치 저장으로 변경

## 👀 ETC
- (해당 없음)
```

## 신선한 예시가 더 필요하면
이 파일이 오래됐다 싶으면 직접 최근 이력을 확인해서 참고할 것:
```bash
gh issue list --repo UNITON-3DAYS/FLIP-Backend --state all --limit 10 --json number,title,body
```
