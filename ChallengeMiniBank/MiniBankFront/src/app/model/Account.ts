export class Account{

    accountId:number;
    accountNumber:String;
	customerId:number;
    accountType:String;
    accountStatus:String;
	exemptOfGmf:boolean=false;
	accountCreationDate:Date;
	accountBalance:number;

    constructor(){};

    setCustomerId(customerId:number):void{
        this.customerId=customerId;
    }

    getCustomerId():number{
        return this.customerId;
    }

}