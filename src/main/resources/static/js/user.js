
document.addEventListener('DOMContentLoaded', function () {

    //회원가입
    const btnJoin= document.querySelector('#btn-join');
    btnJoin.addEventListener('click', joinForm, false);

});

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

     if (response.ok) {
         alert("회원가입이 완료되었습니다.");
         location.href="/";
     } else {
         let error  = await response.json();
         alert(JSON.stringify(error));
     }
}



