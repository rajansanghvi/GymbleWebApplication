'use strict';

App.factory('StudentService', ['$http', '$q', function($http, $q){

	return {
		populateActivityDropDown : function(){
			return $http.get('http://localhost:8080/GymbleWebApplication/list-activities/')
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching users');
						return $q.reject(errResponse);
					}
			); 
		},
		populateBatchesDropDown : function(x){
			return $http.get('http://localhost:8080/GymbleWebApplication/getActivityWiseBatch/'+x)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while creating user');
						return $q.reject(errResponse);
					}
			);
			
		},
			fetchAllUsers: function() {
					return $http.get('http://localhost:8080/GymbleWebApplication/newstudent/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createUser: function(user){
					return $http.post('http://localhost:8080/GymbleWebApplication/newstudent/', user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateUser: function(user, id){
					return $http.put('http://localhost:8080/GymbleWebApplication/newstudent/'+id, user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating user');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteUser: function(id){
					return $http['delete']('http://localhost:8080/GymbleWebApplication/newstudent/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting user');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
