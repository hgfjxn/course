namespace java win.hgfdodo.course.user

struct User{
    1:i32 id,
    2:string username,
    3:string password,
    4:string realname,
    5:string email,
    6:string phone,
    7:bool teacher
}

service UserService{
    void signUp(1:User user);
    User getUserById(1:i32 id);
    User getUserByName(1:string username);
}