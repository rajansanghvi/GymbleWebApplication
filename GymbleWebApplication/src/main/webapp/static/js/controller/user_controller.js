'use strict';

App.controller('StudentController', ['$scope', 'StudentService', function($scope, StudentService) {
          var self = this;
          self.user={id:null,firstName:'',middleName:'',LastName:'',address:{id:null,addressLine1:'',
        	  addressLine2:'',city:'',state:'',country:'',pincode:'',otherDetails:''},
        	  medical:{id:null,bloodGroup:'',medicinesRequired:'',otherSpec:''},
        	  studentInfo:{id:null,guardianName:'',DOB:''},
        	  email:'',phone:'',emergencyContact:'',userCode:'',batches:[{id:null,code:''}]
        		  };
          self.users=[];
          //self.selectedTestAccount = null;
          self.activity = {id:null,name:'',code:''};
          self.activities = [];
          self.batches =[];
          self.batch={id:null,code:''};
          var selectedActivityId = 0;
          

          self.populateActivityDropDown = function(){
        	  StudentService.populateActivityDropDown()
        	  .then(
        			  function(d){
        				  self.activities = d;
        			  },
        			  function(errResponse){
        				  console.error('Error while fetching students');  
        			  }
        			  );
        	  
          };
          self.populateBatchesDropDown =function(){
        	  selectedActivityId = document.getElementById("act").value;
        	StudentService.populateBatchesDropDown(selectedActivityId)
        	.then(
        			function(d){
        				self.batches=d;
        			},
        			function(errResponse){
        				  console.error('Error while fetching students');
        			}
        			);
          };
              
//          self.fetchAllUsers = function(){
//        	  StudentService.fetchAllUsers()
//                  .then(
//      					       function(d) {
//      						        self.users = d;
//      					       },
//            					function(errResponse){
//            						console.error('Error while fetching students');
//            					}
//      			       );
//          };
           
          self.createUser = function(user){
        	  StudentService.createUser(user)
		              .then(
                      self.fetchAllUsers, 
				              function(errResponse){
					               console.error('Error while creating User.');
				              }	
                  );
          };

         self.updateUser = function(user, id){
        	 StudentService.updateUser(user, id)
		              .then(
				              self.fetchAllUsers, 
				              function(errResponse){
					               console.error('Error while updating User.');
				              }	
                  );
          };

         self.deleteUser = function(id){
        	 StudentService.deleteUser(id)
		              .then(
				              self.fetchAllUsers, 
				              function(errResponse){
					               console.error('Error while deleting User.');
				              }	
                  );
          };

         // self.fetchAllUsers();
          self.populateActivityDropDown();
          self.submit = function() {
              if(self.user.id==null){
                  console.log('Saving New User', self.user);    
                  self.createUser(self.user);
              }else{
                  self.updateUser(self.user, self.user.id);
                  console.log('User updated with id ', self.user.id);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.users.length; i++){
                  if(self.users[i].id == id) {
                     self.user = angular.copy(self.users[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.user.id === id) {//clean form if the user to be deleted is shown there.
                 self.reset();
              }
              self.deleteUser(id);
          };

          
          self.reset = function(){
              self.user={id:null,username:'',address:'',email:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
App.controller('activityController',['$scope','$routeParams',function($scope,$routeParams){
	//$scope.person=$scope.people[$routeParams.id]
}]);
