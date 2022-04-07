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

    if(error['status'] == '200'){
        alert("글쓰기가 완료되었습니다.");
        location.href="/";
    }else{
        alert("글쓰기를 실패했습니다.");
    }
}

async function boardDelete(){
    /*
    글 삭제
    */
    let boardId = document.querySelector('#boardId').value;
    let url = "/api/board/"+boardId

    let response = await fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    });
    let error  = await response.json();

    if(error['status'] == '200'){
        alert("게시글 삭제되었습니다.");
        location.href="/";
    }else{
        alert("게시글 삭제를 실패했습니다.");
    }

}





