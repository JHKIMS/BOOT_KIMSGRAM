// (1) 회원정보 수정
function update(userId, event) {

    event.preventDefault(); // Form태그의 새로고침을 막아준다.(폼 태그 액션을 막아준다.)

    let data = $("#profileUpdate").serialize();

    $.ajax({
        type: "put",
        url : `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res=>{ // HTTPSTATUS 상태코드 200
        console.log("업데이트 성공",res);
        location.href=`/user/${userId}`;
    }).fail(error=>{ // HTTPSTATUS 상태코드 200번대가 아닐 경우에
        if(error.data==null){
            alert(error.responseJSON.message);
        }else{
            alert(JSON.stringify(error.responseJSON.data));
        }
    })

}