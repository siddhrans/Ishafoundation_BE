'use strict';
var myApp = angular.module("loginApp",['ngRoute']);
myApp.controller("loginController",['$log','$scope','localService','$http',function($log,$scope,localService,$http){
	$log.log("------------Controller-----------");
	
	
	var self = this;
	self.registerData = {userName:'',name:'',email:'',
			phoneNo:'',password:'',confirmPassword:''};
	
	self.donor_info={donorName:"",donorPhoneNumber:"",donorEmail:"",donorRefName1:"",donorRefName2:"",donorRegion:"",donorState:"",donorCity:"",donorcenter:"",bankAccountholderName:"",
	         bankName:"",bankBranch:"",bankAccountNumber:"",bankMICR:"",bankIfscCode:"",bankAccountType:"",bankUMRN:"",donationStartDate:"",
	         donationEndDate:"",donationFrequency:"",createdonordate:"",creatorName:"",donorComments:""};
	
	
	
/*	self.registerd = [];*/
	self.SuccessMessage='';

	self.DonorsList = [];
	self.successMessage = '';
    self.errorMessage = '';
	self.onlyNumbers = /^\d+([,.]\d+)?$/;
	
	/*var url='http://localhost:6512/donor1/';*/
	self.editUser = {};
	self.logs={phoneNo:'',password:''};
/*	self.logedUser=[];*/

	/* drop down validation */	
	$scope.type = [{
        id: "a",
        value: "SB"
    }, {
        id: "b",
        value: "CA"
    },{
    	id: "c",
        value: "SB-NRE"
    },{ 
    	id: "d",
        value: "SB-NR0"
    },{
	    id: "e",
       value: "Other"
    }];
	
	$scope.amountFrequency = [{
        id: "a",
        value: "Daily"
    }, {
        id: "b",
        value: "Weekly"
    },{
    	id: "c",
        value: "Quarterly"
    },{ 
    	id: "d",
        value: "Semi-Annually"
    },{
	    id: "e",
        value: "Yearly"
    },{
        id: "f",
        value: "Bi-Monthly"
    },{
        id: "g",
        value: "As And When Presented"
    }];
 
	
	self.register = function register(){
		$log.log("-----Register Function-----");
		if(self.registerData.password == self.registerData.confirmPassword){
		localService.register(self.registerData)
			.then(
				function(d){
					self.registerd = d;
					self.SuccessMessage='New User Registerd SuccessFully';
					window.location.href="http://localhost:8080/index.html#!/dashboard";
					self.ErrorMessage='';
				},	
				function(errResponse){
					self.ErrorMessage='Error while Creating!Please try again later';
				}
			);
		}else {
			self.ErrorMessage="Passwords didn't match!! Please try again.";
			window.location.href="http://localhost:8080/login.html";
			$log.log("Error while Registering");
		}
		
	};

	self.fetchDonors = function fetchDonors(){
		$log.log("-----Fetch Donors------");
		localService.fetchDonors()
		.then(
				function(fetchResponse){
					$log.log("successfully fetched data");
					self.DonorsList = fetchResponse;
					self.OldDonors = self.DonorsList.length;
			
		},function(errResponse){
			$log.log("Error while fetching");
			/*alert("Error while fetching");*/
		})
		
	};
	self.fetchDonors();
	
	
	self.createDonor =  function createDonor() {
        console.log('create donor');
        
        console.log(self.donor_info);
        localService.createDonor(self.donor_info)
            .then(
                function (response) {
                    console.log('donor created successfully');
                    self.successMessage = 'donor created successfully';
                    alert('Donor Created successfully')
                    window.location.href="#!SuccessPage";
                    self.errorMessage='';
                    self.done = true;
                 /*   self.user={};*/
                },
                function (errResponse) {
                    console.error('Error while creating donor');
                    alert('Error while creating Donor');
                 
                 /*   self.errorMessage = 'Error while creating donor:' + errResponse.data.errorMessage;
                    self.successMessage='';*/
                }
            );
    };
     self.update = function update(selectedContact,id){
    	 selectedContact = self.selectedContact;
    	 id=self.selectedContact.donorId;
    	 localService.update(id,selectedContact)
    	 .then(function(d){
    		 self.DonorsList = d; 
    	 },function(errResponse){
    		 $log.log("Error loging in controller");
    	 });    	 
     };
     
	self.edit = function edit(id){
		$log.log("The id to be Edited"+id);
		for(var i=0; i<self.DonorsList.length;i++){
			if(self.DonorsList[i].consumerCode == id){
			self.donor_info = angular.copy(self.DonorsList[i]);
			break;
			}
		}
	}
	self.selectContact = function(index){
		self.selectedContact = self.DonorsList[index];
	}
	
	self.login=function login(){
		$log.log(self.logs);
		localService.login(self.logs)
		.then(function(d){
			self.logedUser = d;
			window.location.href="http://localhost:8080/index.html#!/dashboard";
		},function(error){
			$log.log("Error loging in controller");
			alert("Unable to log-in");
		});
		
	}
	
	
}]);
myApp.config(function($routeProvider){
	$routeProvider
/*	.when("/",{
		templateUrl : "login.html"
	})*/
	.when("/dashboard",{
		templateUrl : "dashboard.html"
	})
	.when("/donor_info",{
		templateUrl : "donor_info.html"
		
	})
	.when("/CreateDonor",{
		templateUrl : "CreateDonor.html"
	})
	.when("/ViewDonor",{
		templateUrl : "ViewDonor.html"
	})
	.when("/SuccessPage",{
		templateUrl : "SuccessPage.html"
	})
	.when("/EditDonor",{
		templateUrl : "EditDonor.html"
	})
});