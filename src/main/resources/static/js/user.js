
document.addEventListener('DOMContentLoaded', function () {
    const btnSave = document.querySelector('#btn-save');
    btnSave.addEventListener('click', addAccount, false);
});

async function addAccount(){
    /*
    Users 회원가입
    */
    let data ={
        username : document.querySelector('#username').value,
        password : document.querySelector('#password').value,
        email : document.querySelector('#email').value
    }

    console.log(data);

    let url = "/api/user"
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    });

     if (response.ok) {
         //= await response.json();
         alert("회원가입이 완료되었습니다.");
         location.href="/";
     } else {
         let error  = await response.json();
         alert(JSON.stringify(error));
     }

}




