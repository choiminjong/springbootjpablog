async function joinForm(){
    /*
    Users 회원가입
    */
    let data ={
        username : document.querySelector('#username').value,
        password : document.querySelector('#password').value,
        email : document.querySelector('#email').value
    }

    let url = "/auth/joinProc"
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    });

    let error  = await response.json();

    if(error['status'] == '200'){
        alert("회원가입이 완료되었습니다.");
        location.href="/";
    }else{
        alert("회원가입을 실패했습니다.");
    }
}

async function userUpdate(){
    /*
    User 회원정보 수정
    */
    let data ={
        id : document.querySelector('#id').value,
        username : document.querySelector('#username').value,
        password : document.querySelector('#password').value,
        email : document.querySelector('#email').value
    }

    let url = "/user"
    let response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    });

    let error  = await response.json();

    if(error['status'] == '200'){
        alert("회원수정이 완료되었습니다.");
        location.href="/";
    }else{
        alert("회원수정이 실패했습니다.");
    }
}




