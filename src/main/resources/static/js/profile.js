/**
  1. 유저 프로파일 페이지
  (1) 유저 프로파일 페이지 팔로우하기, 구독취소
  (2) 팔로워 정보 모달 보기
  (3) 유저 프로필 사진 변경
  (4) 사용자 정보 메뉴 열기 닫기
  (5) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
  (6) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
  (7) 팔로우 정보 모달 닫기
 */

// (1) 유저 프로파일 페이지 팔로우하기, 팔로우취소
function toggleFollow(toUserId, obj) {
	if ($(obj).text() === "팔로우 취소") {

		$.ajax({
			type: "delete",
			url: "/api/follow/"+toUserId,
			dataType:"json"
		}).done(res => {
			$(obj).text("팔로우");
			$(obj).toggleClass("black");
		}).fail(error => {
			console.log("팔로우 취소 실패", error);
		});


	} else {

		$.ajax({
			type: "post",
			url: "/api/follow/"+toUserId,
			dataType:"json"
		}).done(res => {
			$(obj).text("팔로우 취소");
			$(obj).toggleClass("black");
		}).fail(error => {
			console.log("팔로우 하기 실패", error);
		});

	}
}

// (2) 팔로우 정보  모달 보기
function followInfoModalOpen(pageUserId) {
	$(".modal-subscribe").css("display", "flex");

	$.ajax({
		url:`/api/user/${pageUserId}/follow`,
		dataType:"json"
	}).done(res=>{
		console.log(res.data);

		res.data.forEach((followUser)=>{
			let item=getSubscribeModalItem(followUser);
			$("#subscribeModalList").append(item);
		})
	}).fail(error=>{
		console.log("팔로우 창 불러오기 오류", error);
	});

}

function getSubscribeModalItem(followUser) {

	let item = `<div class="subscribe__item" id="subscribeModalItem-${u.id}">
					<div class="subscribe__img">
						<img src="/upload/${followUser.profileImageUrl}" onerror="this.src='/images/person.jpeg'"/>
					</div>
					<div class="subscribe__text">
						<h2>${followUser.username}</h2>
					</div>
					<div class="subscribe__btn">`;

					if(!followUser.equalUserState){ // 동일 유저가 아닐 때 버튼이 만들어져야 한다.
						if(followUser.followState){ // 팔로우한 상태
							item+= `<button class="cta black" onclick="toggleFollow(${followUser.id}, this)">팔로우 취소</button>`;
					}else{ // 팔로우 안한 상태
							item+= `<button class="cta" onclick="toggleFollow(${followUser.id}, this)">팔로우</button>`;
						}
					}
					item+=`
					</div>
				</div>`;

	return item;
}


// (3) 유저 프로파일 사진 변경 (완)
function profileImageUpload() {
	$("#userProfileImageInput").click();

	$("#userProfileImageInput").on("change", (e) => {
		let f = e.target.files[0];

		if (!f.type.match("image.*")) {
			alert("이미지를 등록해야 합니다.");
			return;
		}

		// 사진 전송 성공시 이미지 변경
		let reader = new FileReader();
		reader.onload = (e) => {
			$("#userProfileImage").attr("src", e.target.result);
		}
		reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
	});
}


// (4) 사용자 정보 메뉴 열기 닫기
function popup(obj) {
	$(obj).css("display", "flex");
}

function closePopup(obj) {
	$(obj).css("display", "none");
}


// (5) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
function modalInfo() {
	$(".modal-info").css("display", "none");
}

// (6) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
function modalImage() {
	$(".modal-image").css("display", "none");
}

// (7) 팔로우 정보 모달 닫기
function modalClose() {
	$(".modal-subscribe").css("display", "none");
	location.reload();
}






