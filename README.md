# 📱 ContactApp - 71억ᄂrᄂl。。。? ★

## 💻 프로젝트 소개
연락처 앱이라는 주제를 받고 단순히 이름, 전화번호로만 연락처를 저장하는 것이 아닌
이메일, 생일, 주소, 메모 등을 추가하여 우리의 추억을 저장하자…..(아련..) 란 의미를 갖고 시작하게 되었습니다.

## 🔧 프로젝트 상세

### 📆 개발기간
23.09.04 ~ 23.09.11

### 📽️ 시연 영상

https://www.youtube.com/watch?v=vWqgZEVZb-E

### 🎨 와이어프레임

https://www.figma.com/file/WfnIywVP5UYBaa4HlE33lY/Untitled?type=whiteboard&node-id=8-91&t=bAo24IEPrtmbEmW3-0

### 👥 개발자
+ ‍👩‍💻 황진주‍
    - https://github.com/jinjoo1
+ ‍🧑‍💻 윤동현
    - https://github.com/youneeo
+ ‍🧑‍💻 이성진
    - https://github.com/asdsad86642
+ ‍🧑‍💻 이종민
    - https://github.com/ljmin94
+ ‍👩‍💻 서수현
    - https://github.com/joye-seo

## 📌 주요 기능

### HomeActivity
* 작업자 : 서수현
* BottomNavigation - ViewPager2 연결하여 스와이프 기능 구현

### ContactFragment
* 작업자 : 윤동현, 이종민, 서수현
* Recyclerview 로 리스트 및 기능구현, 즐겨찾기 기능 구현
* ItemTouchHelper(onMove, onSwiped) 사용하여 오른쪽 스와이프 시 전화 연결
*  연락처 저장 시 sort를 이용해 이름순으로 추가가 됨 
* star 버튼 클릭 시 맨 상단으로 올라가고 star버튼 클릭된 리스트도 이름순으로 정렬됨

### KeypadFragment
* 작업자 : 이성진
* GridLayout으로 키패드 xml 작성
* 영상통화 클릭시  다이얼로그 메세지 띄우기
* Intents, Permissions,을 사용하여 전화 기능 구현

### HistoryFragment
* 작업자 : 이성진
* 리싸이클리어뷰로 화면 구현
* 클릭시 숨긴 텍스트 더미데이터를 가져와서 화면에 출력

### MypageFragment
* 작업자 : 황진주
* 원하는 메시지 어플을 이용하여 내 정보 공유 가능
* 수정시 이미지 즉시 반영, 
* 수정한 후 에뮬을 껐다 켜도 유지 및 저장

### ContactAddFragment
* 작업자 : 서수현
* 추가항목 보기 버튼 클릭 시 버튼 gone처리 되면서 추가항목 데이터 보여짐 
* 필수항목 데이터 추가 되지 않으면 값이 넘어가지 않음 
* 생일 클릭 시 달력dialog띄어지고 pick한 값이 입력됨 
* 맞지 않는 데이터 (ex 이메일 주소 형식이 맞지 않을 때 aaa@bbb.ccc) 에러문구 띄어짐 
* 이미지 갤러리에서 불러옴 (리스트에 추가는 구현 못함)

### ContactDetailActivity
* 작업자 : 이종민, 서수현
* ScroView 로 화면 구현
* Contactpage 아이템 클릭 시 더미데이터 화면에 출력
* Contact 별 버튼 클릭 시 Detail에도 별 색이 변경되어 있음


