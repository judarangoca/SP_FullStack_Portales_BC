export class Account{

    accountId:number;
    customerId:number;
    accountType:String;
    accountNumber:String;
    accountStatus:String;
    accountBalance:number;
    accountCurrentBalance:number;
	exemptOfGmf:boolean;
	accountCreationDate:Date;
    creationUser:String;
    modificationDate:Date;
    modificationUser:String;

    constructor(){};

    setCustomerId(customerId:number):void{
        this.customerId=customerId;
    }

    getCustomerId():number{
        return this.customerId;
    }

}