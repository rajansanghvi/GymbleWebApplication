<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
		<script src="http://code.angularjs.org/1.4.4/angular-route.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/user_service.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/user_controller.js' />"></script>
<title>Gymble Student Registeration</title>
<style>
.username.ng-valid {
	background-color: lightgreen;
}

.username.ng-dirty.ng-invalid-required {
	background-color: red;
}

.username.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.email.ng-valid {
	background-color: lightgreen;
}

.email.ng-dirty.ng-invalid-required {
	background-color: red;
}

.email.ng-dirty.ng-invalid-email {
	background-color: yellow;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Student Registration Form </span>
			</div>
			<a href="#/masters/activity">Activity</a>
			<div class="formcontainer" ng-controller="StudentController as ctrl">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.user.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">First
								Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.firstName" name="uname"
									class="username form-control input-sm"
									placeholder="Enter your name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.uname.$error.required">This is a
										required field</span> <span ng-show="myForm.uname.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.uname.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Middle
								Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.middleName" name="mname"
									class="username form-control input-sm"
									placeholder="Enter your name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.uname.$error.required">This is a
										required field</span> <span ng-show="myForm.uname.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.uname.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Last
								Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.lastName" name="lname"
									class="username form-control input-sm"
									placeholder="Enter your name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.uname.$error.required">This is a
										required field</span> <span ng-show="myForm.uname.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.uname.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Guardian
								or Parent Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.studentInfo.guardianName"
									name="mname" class="username form-control input-sm"
									placeholder="Enter your name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.uname.$error.required">This is a
										required field</span> <span ng-show="myForm.uname.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.uname.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Address
								Line1</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.address.addressLine1"
									class="form-control input-sm"
									placeholder="Enter your Address. [This field is validation free]" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Address
								Line2</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.address.addressLine2"
									class="form-control input-sm"
									placeholder="Enter your Address. [This field is validation free]" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">City
						</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.address.city"
									class="form-control input-sm"
									placeholder="Enter your city. [This field is validation free]" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">State
								</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.address.addressLine2"
									class="form-control input-sm"
									placeholder="Enter your Address. [This field is validation free]" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Country
								</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.address.addressLine2"
									class="form-control input-sm"
									placeholder="Enter your Address. [This field is validation free]" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Blood Group
								</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.medical.bloodGroup"
									class="form-control input-sm"
									placeholder="Enter your Blood Group. [This field is validation free]" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Other Specificaions
								</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.medical.otherSpec"
									class="form-control input-sm"
									placeholder="Enter your Blood Group. [This field is validation free]" />
							</div>
						</div>
					</div>
				<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">DOB</label>
							<div class="col-md-7">
								<p ng-bind="ctrl.user.studentInfo.DOB | date:'MM/dd/yyyy'"></p>
							</div>
						</div>
					</div>
					
					
						
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Email</label>
							<div class="col-md-7">
								<input type="email" ng-model="ctrl.user.email" name="email"
									class="email form-control input-sm"
									placeholder="Enter your Email" required />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.email.$error.required">This is a
										required field</span> <span ng-show="myForm.email.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="{{!ctrl.user.id ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
								Form</button>
						</div>
					</div>
					
				</form>
					
				<!-- <b>Activity Data:</b> <select id="ctrl.activity" ng-model="activities">
						<option value="">-- Select Activities --</option>
						<option id ="act" data-ng-repeat="personData in ctrl.activities" data-ng-change="populateBatchesDropDown()" value="{{personData.id}}" >{{personData.name}}</option>
					</select><br> -->
					
					<select ng-model="ctrl.activity"                
    				ng-options="obj.id as obj.name for obj in ctrl.activities"
   					ng-change="ctrl.populateBatchesDropDown()"
    				class="form-control" 
    				ng-required="true"
    				id="act">
    				<option value="">-- Choose Activity --</option>
					</select>      
					
					
				<!-- <b>Batches:</b> <select id="ctrl.batch">
						<option value="">-- Select Batch --</option>
						<option data-ng-repeat="personData in ctrl.batches"  value="{{personData.id}}">{{personData.name}}</option>
				</select><br> -->
				<select ng-model="ctrl.batch" 
    			ng-options="x.id as x.code for x in ctrl.batches"
    			class="form-control"
    			ng-required="true"
    			id="batch">
    			<option value="">-- Choose Batch --</option>
				</select>    
				</div>
				</div>
		
		<!-- <div class="panel panel-default">
			Default panel contents
			<div class="panel-heading">
				<span class="lead">List of Users </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>First Name</th>
							<th>Middle Name</th>
							<th>Last Name</th>
							<th>Parent or Guardian Name</th>
							<th>Date Of Birth</th>
							<th>Mobile no.</th>
							<th>Email ID</th>
							<th>Emergency Contact</th>
							<th>Address Line1</th>
							<th>Address Line2</th>
							<th>City</th>
							<th>State</th>
							<th>Country</th>
							<th>PinCode</th>
							<th>Other Details</th>
							<th>Blood Group</th>
							<th>Medicines Required</th>
							<th>Allergies or other Medical Specifications</th>
							<th width="20%"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.users">
							<td><span ng-bind="u.id"></span></td>
							<td><span ng-bind="u.username"></span></td>
							<td><span ng-bind="u.address"></span></td>
							<td><span ng-bind="u.email"></span></td>
							<td>
								<button type="button" ng-click="ctrl.edit(u.id)"
									class="btn btn-success custom-width">Edit</button>
								<button type="button" ng-click="ctrl.remove(u.id)"
									class="btn btn-danger custom-width">Remove</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div> -->
	</div>
</body>
</html>