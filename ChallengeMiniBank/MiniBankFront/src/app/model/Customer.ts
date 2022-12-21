export class Customer{

	customerId: number; 
	identificationType: String;
	identificationNumber: String;
	firstName: String;
	lastName: String;
	email: String;
	birthDate: Date;
	phoneNumber: String;
	creationDate:Date;
	modificationDate:Date;
	userModificator:String;


	constructor(customerId: number,
		identificationType: String,
		identificationNumber: String,
		firstName: String,
		lastName: String,
		email: String,
		birthDate: Date,
		phoneNumber: String,
		creationDate:Date,
		modificationDate:Date,
		userModificator:String){
			this.customerId=customerId;
			this.identificationType=identificationType;
			this.identificationNumber=identificationNumber;
			this.firstName=firstName;
			this.lastName=lastName;
			this.email=email;
			this.birthDate=birthDate;
			this.phoneNumber=phoneNumber;
			this.creationDate=creationDate;
			this.modificationDate=modificationDate;
			this.userModificator=userModificator
		}

	
}