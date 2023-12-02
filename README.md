## 인트로

자취를 하면서 가족, 친구같은 사람들이 집에 올 때, 비밀번호를 알려주기는 꺼려지고, 제가 없을 때도 집에 출입할 수 있게 하기 위해 열쇠 대신 NFC 카드를 나눠주고 출입 제어를 하는 프로젝트를 제작했습니다.

## 기술스택
### 프론트엔드

<table class="images_box" border="0">
	<tr>
		<td class="image">
			<image src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1566879300/noticon/fvty9lnsbjol5lq9u3by.svg" width="120px" height="120px"/>
		</td>
						<td class="image">
			<image src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1613069004/noticon/ugcstxkq5uzhbhknrr80.png" width="120px" height="120px"/>
		</td>
						<td class="image">
			<image src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1691760811/noticon/mj67k1iwrb1vjrrntob2.png" width="120px" height="120px"/>
		</td>
						<td class="image">
			<image src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1567749614/noticon/zgdaxpaif5ojeduonygb.png" width="120px" height="120px"/>
		</td>
	</tr>
	<tr>
		<td class="text">Next.js 13</td>
		<td class="text">React Native</td>
		<td class="text">SWR</td>
		<td class="text">Redux</td>
	</tr>
</table>

### 백엔드
<table class="images_box">
	<tr>
		<td class="image">
			<image src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1583139980/noticon/vtzecmjzn39cifnjtonx.png" width="120px" height="120px"/>
		</td>
		<td class="image">
			<image src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1687307488/noticon/o9lxyva5z8zbwyeaxers.png" width="120px" height="120px"/>
		</td>
		<td class="image">
			<image src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1686935854/noticon/r7w1ipwmdmhlfzqfw69h.png" width="120px" height="120px"/>
		</td>
	</tr>
	<tr>
		<td class="text">Spring Boot</td>
		<td class="text">Spring Data JPA</td>
		<td class="text">Spring Security</td>
	</tr>

</table>

### 데브옵스 (현재 배포되어 있지 않습니다)
<table class="images_box">
	<tr>
		<td class="image">
			<image src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1629987467/noticon/o0ua9qgyuuoazjqwwvas.png" width="120px" height="120px"/>
		</td>
		<td class="image">
			<image src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1683592944/noticon/plcvyiwmdh4adlt8dxgo.png"  width="120px" height="120px"/>
		</td>
	</tr>
	<tr>
		<td class="text">AWS EC2</td>
  		<td class="text">AWS RDS</td>
	</tr>

</table>

# 아키텍쳐
![아키텍쳐](https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/Doorlock%E1%84%8B%E1%85%A1%E1%84%8F%E1%85%B5%E1%84%90%E1%85%A6%E1%86%A8%E1%84%8E%E1%85%A7.jpg)

# 개발 과정

### 개발 초기 과정

- React 디자인 패턴
    
    - 디자인 패턴을 채용한 이유
        - 프로젝트를 진행하면서 무분별하게 JSX 를 생성하다보면 컴포넌트가 늘어날수록 필요한 컴포넌트를 찾을 때, 각각의 컴포넌트가 무슨 역할을 하고 있는지 알 수 없는 경우가 있었습니다.
        - 그렇기 때문에 디자인 패턴을 적용해서 기준을 가지고 컴포넌트를 구분하면 효율적인 개발이 가능할 것이라 판단해서 VAC(View Asset Component) 패턴을 기반으로 컴포넌트를 설계했습니다.
    - View, Asset, Component를 나누는 기준
        - Next.js 프로젝트에서 View 는 Apps 이하의 페이지 JSX, Component는 데이터를 다루는 비즈니스 로직을 담당하는 JSX, Asset은 Fetch 해서 가져온 데이터를 Props로 받는 JSX로 정의하고 이 기준에 맞춰서 컴포넌트를 구분했습니다.
- 모바일 개발에 React Native를 사용하게 된 과정
    
    1. 기획 단계에서
        
        처음에는 React Native가 필요하지 않았습니다.
        
        - 처음의 프로젝트 기획 단계에서 카드를 태그할 수 있고, API 를 지원하는 도어락 기기가 있다는 전제로 시작했습니다.
        - 전제를 가정하고 개발을 시작했고, 개발 중에 계속 조건에 맞는 도어락 기기를 찾고 있는데 해외 사이트에서도 발견하지 못해서 태그 역할만 수행할 NFC 단말기를 사용하기로 했습니다.
        
        이후에 React Native를 기획에 포함시킨 이유
        
        - 프로젝트의 핵심 기능인 카드를 태그하는 기능은 NFC를 태그할 수 있는 기능은 전용 NFC 단말기를 구하거나, NFC 기능이 포함되어 있는 스마트폰을 사용하는 2가지 경우의 수가 있습니다.
        - NFC 단말기는 결국 다시 어딘가에 연결해서 카드 데이터를 처리해야기 때문에 스마트폰을 NFC를 태그하는 기기로 사용하기로 했습니다.
        - 개발하는 어플은 어드민 권한을 가진 카드를 태그했을 때, 어드민 페이지의 기능을 사용할 수 있게 기획했습니다.
    2. 개발을 준비하면서
        
        - 이전에 주로 Next.js 를 기반으로 한 웹 서비스를 개발해왔기 때문에 네이티브 모바일 개발을 시작하는 것은 러닝 커브가 발생하고, 이는 곧 프로젝트의 개발 기간이 길어지는 이슈가 발생할 가능성이 컸습니다.
        - 이를 해결하기 위해 주로 사용하는 JavaScript를 기반으로 모바일을 개발할 수 있는 React Native를 사용하기로 했습니다.
    3. 개발을 진행하면서
        
        - 네이티브 대신 React Native로 개발하면서 가장 도움이 되었던 부분은, 웹 서비스를 개발하면서 사용해왔던 Redux 와 SWR를 그대로 가져와서 사용할 수 있었던 점이었습니다.
        - 특히 상태관리에 있어서 JSON 형식으로 상태를 관리하는 것은 자바/코틀린과 다르게 매우 직관적이고 편하다고 생각하고 있기 때문에 Redux를 사용할 수 있는 것은 편하고 수월하게 개발할 수 있게 도움이 되었습니다.
- React Native에서 절대 경로(path alias) 설정
    
    절대 경로를 사용하는 이유
    
    - 우선 디자인 패턴에 맞춰서 View, Asset, Component를 폴더를 생성하고, 각각에 맞춰서 Card, Room, User, Reservation 등 항목에 맞춰서 폴더를 생성하고, 그 하위 항목의 폴더를 생성하다보니 import 하기 위해 상대 경로를 입력하면 `../` 의 반복이 너무 많아서 경로를 입력할 때, 너무 복잡해지는 이슈가 있었습니다.
    - 절대 경로를 사용하면 import 하기 위한 경로의 길이는 길어질 수 있지만, 루트 위치가 고정이기 때문에 알아보기 쉽다는 점을 높게 평가해서 절대 경로를 사용하기로 했습니다.
    
    절대 경로를 사용하기 전에 생긴 문제에 대하여
    
    - Next.js 에서는 프로젝트 파일를 생성할 때부터 절대 경로를 사용 여부를 확인해서 자동으로 설정해주기 때문에 절대 경로에 대한 환경 설정이 필요하지 않았습니다.
    - 하지만 React Native로 넘어오면서 절대 경로에 대한 환경을 구비해야 한다는 것을 몰랐기 때문에 계속 오류를 마주쳤습니다.
    
    환경 설정 과정
    
    - 우선 tsconfig.json 에서 기본적인 절대 경로에 대한 설정을 추가했습니다.
        <image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/tsconfig.png"/>
        
        
    - 컴파일 과정에서 절대 경로 표시 (@/src/**) 를 인식할 수 있도록 packge.json에 babel-plugin-module-resolver 를 추가하고 babel.config.json 에서 plugins 도 추가했습니다.
        <image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/babel.png" height="360px"/>
        

### 디자인 과정

- 모바일에서 대세인 메뉴는?
    - 초기에 메뉴를 설계할 때, 단순히 처음 화면에서 버튼 목록을 생성하고, 버튼을 통해 화면을 이동했다가 돌아오는 방식으로 설계했었습니다.
        <image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%86%E1%85%A9%E1%84%87%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%AF+%E1%84%86%E1%85%A6%E1%84%82%E1%85%B2+%E1%84%80%E1%85%AA%E1%84%80%E1%85%A5.png" height="540px"/>
        <div>초기 메뉴 설계 예시 (wekdev의 NFC Tools)</div>
        
    - 하지만 다른 화면을 가기위해서는 항상 처음 화면에 돌아와야 한다는 것에, UX 관점에서 불편함을 많이 느꼈습니다.
        
    - 이를 해결하기 위해 제가 사용하는 어플에서는 어떻게 메뉴가 설계 되어 있을까 확인 해봤습니다.
        
    - 우선 기본 어플인 전화, 앱스토어에서 부터 카톡, 유튜브, 인스타그램, Gmail 등, 대부분의 어플의 메뉴는 아이콘이 하단에 있는 Bottom Tab 방식이었습니다.
        
        <image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%86%E1%85%A9%E1%84%87%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%AF+%E1%84%86%E1%85%A6%E1%84%82%E1%85%B2+%E1%84%92%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%A2.png" height="540px"/>
        <div>Bottom Tab 방식 (Gmail)</div>
        
    - 많은 어플이 이 방식을 쓰고 있기 때문에 디자인적으로 선호되고 있다고 판단해, 초기 메뉴 방식에서 Bottom Tab 방식으로 메뉴를 구성했습니다.
        
- 목록 표현 방식
	- 각종 커뮤니티 사이트를 비롯한 게시판에서 게시글을 나열할 때, 가장 첫번째 열에는 글 제목, 작성자, 조회수, 작성일 등 컬럼을 표시하고 그 밑으로 각각의 게시글을 나열합니다.
	    <div><image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%8F%E1%85%A5%E1%84%86%E1%85%B2%E1%84%82%E1%85%B5%E1%84%90%E1%85%B5+%E1%84%80%E1%85%A6%E1%84%89%E1%85%B5%E1%84%80%E1%85%B3%E1%86%AF+%E1%84%8B%E1%85%A8%E1%84%89%E1%85%B5.png" height="540px"/></div>
	    <div>첫번째 열에는 컬럼, 두번째 열부터 게시글이 나열됨(네어버 언리얼 엔진 공식카페)</div>
    
- 가장 무난한 방식이긴 하지만, 컬럼 수가 늘어날수록 화면의 가로 폭이 넓어야 합니다.
    
- 가로 폭이 넓어야 하는 점은 모바일에서는 상당히 불리하기 때문에 많은 사이트에서 반응형 웹을 지원할 때, 가장 첫 줄의 컬럼을 제외하고 하나의 게시글당 2줄 이상으로 구성했습니다.
    <div><image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%8F%E1%85%A5%E1%84%86%E1%85%B2%E1%84%82%E1%85%B5%E1%84%90%E1%85%B5+%E1%84%80%E1%85%A6%E1%84%89%E1%85%B5%E1%84%80%E1%85%B3%E1%86%AF+%E1%84%86%E1%85%A9%E1%84%87%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%AF+%E1%84%8B%E1%85%A8%E1%84%89%E1%85%B5.jpeg" height="540px"/></div>
    <div>게시글 첫번째 줄에 제목, 두번째 줄에 나머지 정보가 있음(네어버 언리얼 엔진 공식카페)</div>
    
- 이번 프로젝트에서도 이와 같은 방식으로 카드 정보, 유저 정보를 표시하려 했지만, 디자인으로 프로젝트의 컨셉을 조금이라도 살리고 싶어서 다른 방식을 고안했습니다.
    
- 프로젝트의 목적이 카드키로 도어락 출입 통제를 하는 것이기에, 카드 모양으로 정보를 나타낸다면 컨셉을 더 잘 나타낼 수 있다고 판단해서 단순 리스트에서 카드로 디자인을 변경했습니다.

## Flow Chart

- **로그인**
    <div><image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%85%E1%85%A9%E1%84%80%E1%85%B3%E1%84%8B%E1%85%B5%E1%86%AB-%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A9%E1%84%8B%E1%85%AE%E1%84%8E%E1%85%A1%E1%84%90%E1%85%B3.jpg" height="540px"/></div>
    
- **로그아웃**
    <div><image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%85%E1%85%A9%E1%84%80%E1%85%B3%E1%84%8B%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%BA-%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A9%E1%84%8B%E1%85%AE%E1%84%8E%E1%85%A1%E1%84%90%E1%85%B3.jpg" height="540px"/></div>
    
- **카드 분실 신고**
	<div><image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%8F%E1%85%A1%E1%84%83%E1%85%B3%E1%84%87%E1%85%AE%E1%86%AB%E1%84%89%E1%85%B5%E1%86%AF%E1%84%89%E1%85%B5%E1%86%AB%E1%84%80%E1%85%A9-%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A9%E1%84%8B%E1%85%AE%E1%84%8E%E1%85%A1%E1%84%90%E1%85%B3.jpg" height="540px"/></div>
    
- **카드 분실 신고 취소**
    <div><image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%8F%E1%85%A1%E1%84%83%E1%85%B3%E1%84%87%E1%85%AE%E1%86%AB%E1%84%89%E1%85%B5%E1%86%AF%E1%84%89%E1%85%B5%E1%86%AB%E1%84%80%E1%85%A9%E1%84%8E%E1%85%B1%E1%84%89%E1%85%A9-%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A9%E1%84%8B%E1%85%AE%E1%84%8E%E1%85%A1%E1%84%90%E1%85%B3.jpg" height="540px"/></div>
    
- 분실 카드 처리
    <div><image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%87%E1%85%AE%E1%86%AB%E1%84%89%E1%85%B5%E1%86%AF%E1%84%8F%E1%85%A1%E1%84%83%E1%85%B3%E1%84%8E%E1%85%A5%E1%84%85%E1%85%B5-%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A9%E1%84%8B%E1%85%AE%E1%84%8E%E1%85%A1%E1%84%90%E1%85%B3.jpg" height="540px"/></div>
    
- 예약 변경 요청
    <div><image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%8B%E1%85%A8%E1%84%8B%E1%85%A3%E1%86%A8%E1%84%87%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%E1%84%8B%E1%85%AD%E1%84%8E%E1%85%A5%E1%86%BC-%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A9%E1%84%8B%E1%85%AE%E1%84%8E%E1%85%A1%E1%84%90%E1%85%B3.jpg" height="540px"/></div>
    
- 예약 변경 요청 처리
    <div><image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%8B%E1%85%A8%E1%84%8B%E1%85%A3%E1%86%A8%E1%84%87%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%E1%84%8B%E1%85%AD%E1%84%8E%E1%85%A5%E1%86%BC%E1%84%8E%E1%85%A5%E1%84%85%E1%85%B5-%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A9%E1%84%8B%E1%85%AE%E1%84%8E%E1%85%A1%E1%84%90%E1%85%B3.jpg" height="540px"/></div>
    
- 예약 변경
    <div><image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/%E1%84%8B%E1%85%A8%E1%84%8B%E1%85%A3%E1%86%A8%E1%84%87%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC-%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A9%E1%84%8B%E1%85%AE%E1%84%8E%E1%85%A1%E1%84%90%E1%85%B3.jpg" height="540px"/></div>

## APIs

API 문서는 Postman 으로 작성했습니다.

[Postman API 문서](https://documenter.getpostman.com/view/21615276/2s9YC5xCPu)

## DB 설계
<image src="https://s3.ap-northeast-2.amazonaws.com/ikiningyou.portfolio.s3.bucket/Images/doorlock/db.png"/>
