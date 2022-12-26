const data = document.getElementById("dataUserTable");
const url = 'http://localhost:8080/api/user';

function userInfo() {
    fetch(url)
        .then((res) => res.json())
        .then((user) => {
            let temp = '';
            temp += `<tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.roles.map(r => r.rolename)}</td>
            </tr>`;
            data.innerHTML = temp;
        });
}

userInfo()