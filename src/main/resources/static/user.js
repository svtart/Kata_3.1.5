function getOneUser() {
    fetch("http://localhost:8080/api/user")
        .then(res => res.json())
        .then(user => {
            let temp = '';
            console.log(user);

                temp += `
                <tr>
                <td>${user.id}</td>
                <td>${user.username}</td> 
                <td>${user.age}</td> 
                <td>${user.email}</td>
                <td>${user.roles.map(r => r.rolename)}</td>
                <td>
                
              </tr>`;
            document.getElementById("dataUserTable").innerHTML = temp;
        });
}
getOneUser()