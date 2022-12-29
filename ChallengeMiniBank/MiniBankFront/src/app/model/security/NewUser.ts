export class NewUser{

username:String;
password:String;
name:String;
surname:String;
email:String;

constructor(username:String, password:String, name:String, surname:String, email:String){
    this.username=username;
    this.password=password;
    this.name=name;
    this.surname=surname;
    this.email=email;
}
}