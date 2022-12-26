const data = document.getElementById("dataUserTable");
const url = 'http://localhost:8080/api/user';

function userInfo() {
    fetch(url)
        .then((res) => res.json())
        .then((u) => {
            let temp = '';
            temp += `<tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.age}</td>
            <td>${u.email}</td>
            <td>${u.role}</td>
            </tr>`;
            data.innerHTML = temp;
        });
}

userInfo()