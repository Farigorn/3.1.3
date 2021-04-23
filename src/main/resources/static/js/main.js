const usersPage = document.getElementById("users")

$(usersPage).ready(function () {
    showUserTable();
});

fetch("base/user").then(result => result.json()
    .then(data => user = data)
    .then(() => showUser(user)));

//Создание и заполнение таблицы юзерами
function showUserTable() {
    const usersList = document.querySelector('#userTableBody');
    const url = 'http://localhost:8080/base/users';
    let output = '';
    fetch(url).then(response => response.json())
        .then(data => {
            data.forEach(user => {
                const role = user.roles.map(function (role) {
                    let name = role.name;
                    return name.split('_').pop();
                });
                output += `
                <tr>
                    <td class="pt-3 pl-3" >${user.id}</td>
                    <td class="pt-3 pl-3" >${user.name}</td>
                    <td class="pt-3 pl-3" >${user.lastName}</td>
                    <td class="pt-3 pl-3" >${user.age}</td>
                    <td class="pt-3 pl-3" >${user.email}</td>
                    <td class="pt-3 pl-3" >${role.join(" ")}</td>
                    <td> <button class="btn btn-info eBtn" onclick="ShowModal(${user.id}, 'edit')">Edit</button>
                    </td>
                    <td><button class="btnDelete btn btn-danger" onclick="ShowModal(${user.id}, 'delete')">Delete</button></td>
                </tr>
            `;
            });
            usersList.innerHTML = output;
        });
}

//Функция для назначение роли
function getRoles(roles) {
    let select = document.getElementById(roles)
    if (select.value === "1") {
        return [{
            "id": 2,
            "role": "ROLE_ADMIN",
            "authority": "ROLE_ADMIN"
        },
            {
                "id": 1,
                "role": "ROLE_USER",
                "authority": "ROLE_USER"
            }]
    } else {
        return [{
            "id": 1,
            "role": "ROLE_USER",
            "authority": "ROLE_USER"
        }]
    }
}

//Добавление user
$(".addBtn").on("click", function (event) {
    event.preventDefault();
    let user = {
        id: $("#id").val(),
        name: $("#name").val(),
        lastName: $("#lastName").val(),
        age: $("#age").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        roles: getRoles("roleId")
    };

    if (document.getElementById("add").password.value === "") {
        alert("Пожалуйста укажите пароль.");
    } else {
        fetch("http://localhost:8080/base/add", {
            method: "Post",
            headers: {
                'Content-Type': 'application/json;'
            },
            body: JSON.stringify(user)
        }).then(response => response.json())
            .catch(err => console.log(err))
            .then(() => {
                $('.nav-tabs a[href="#home"]').tab('show')
            }).then(() => showUserTable());
        $('input').val('');
    }
});

//Показ модального окна
function ShowModal(id, str) {
    let url = "base" + "/" + id
    fetch(url).then(response => response.json()).catch(err => console.log(err)).
    then(user => {
            document.getElementById(str + "Id").value = user.id;
            document.getElementById(str + "Name").value = user.name;
            document.getElementById(str + "LastName").value = user.lastName;
            document.getElementById(str + "Age").value = user.age;
            document.getElementById(str + "Email").value = user.email;
            if (str === 'edit') {
                document.getElementById(str + "Password").value = user.password;
            }

        }
    )
    $("#" + str + "Modal").modal()
}

//редактирование Юзера
$(".editBtn").on("click", function (event) {
    event.preventDefault();
    let user = {
        id: $("#editId").val(),
        name: $("#editName").val(),
        lastName: $("#editLastName").val(),
        age: $("#editAge").val(),
        email: $("#editEmail").val(),
        password: $("#editPassword").val(),
        roles: getRoles("editRoleId")
    };
    fetch("base/update", {
        method: "Put",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user),
    }).catch(err => console.log(err))
        .then(function (response) {
        $("#editModal").modal('hide');
        showUserTable();
    })
});

//Удаление юзера
$(".deleteBtn").on("click", function (event) {
    event.preventDefault();
    let id = $("#deleteId").val()
    fetch("base/delete/" + id, {
        method: "Delete"
    }).catch(err => console.log(err)).then(function () {
        $("#deleteModal").modal('hide');
        showUserTable();
    })
});

// показ одного юзера
function showUser(user) {
    const userList = document.querySelector("#userTableOneBody");
    const role = user.roles.map(function (role) {
        let name = role.name;
        return name.split('_').pop();
    });
    let output = '';
    output += `
               <tr>
                   <td class="pt-3 pl-3" >${user.id}</td>
                   <td class="pt-3 pl-3" >${user.name}</td>
                   <td class="pt-3 pl-3" >${user.lastName}</td>
                   <td class="pt-3 pl-3" >${user.age}</td>
                   <td class="pt-3 pl-3" >${user.email}</td>
                   <td class="pt-3 pl-3" >${role.join(" ")}</td>
                </tr>
            `;
    userList.innerHTML = output;
}