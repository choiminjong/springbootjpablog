
document.addEventListener('DOMContentLoaded', function () {

    //회원가입
    const btnBoardSave= document.querySelector('#btn-board-save');
    btnBoardSave.addEventListener('click', boardForm, false);

});

async function boardForm(){
    /*
    글쓰기
    */
    let data ={
        title : document.querySelector('#title').value,
        content : document.querySelector('#content').value
    }

    let url = "/api/board"
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    });
    let error  = await response.json();

    console.log(error)

    if (response.ok) {
        alert("글쓰기가 완료되었습니다.");
        location.href="/";
    } else {

        alert(JSON.stringify(error));
    }

}



