namespace java win.hgfdodo.course.message
namespace py message.api

service MessageService{
    bool sendMobileMessage(1:string mobile, 2:string content);
    bool sendEmailMessage(1:string email, 2:string content);
}