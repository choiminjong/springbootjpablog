
document.addEventListener('DOMContentLoaded', function () {

    //로그인
    const btnLogin = document.querySelector('#btn-login');
    btnLogin.addEventListener('click', login, false);

});

async function login(){
    /*
    로그인
    */
    let data ={
        username : document.querySelector('#username').value,
        password : document.querySelector('#password').value,
    }

    let url = "/api/user/login"
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        alert("로그인이 완료되었습니다.");
        location.href="/";
    } else {
        let error  = await response.json();
        alert(JSON.stringify(error));
    }
}


