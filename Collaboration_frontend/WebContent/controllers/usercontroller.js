

app.controller('UserController',function($scope,UserService,$location,$rootScope,$cookieStore){
	
	
	$scope.registerUser=function(){
		/*
		 * call user service by passing user data in json format
		 * */
		
		console.log("user data is" + $scope.user)
		UserService.registerUser($scope.user).then(function(response){
		/*
		 * redirect the user to login page with 'registration sucess'
		 * */	
			console.log(response.data)
			console.log(response.status)
			$location.path('/home')
			
		},function(response){
			/*
			 * error condition 
			 * if status is 406 , either username is not valid / email is not valid
			 * display the error message in the registrationform
			 * */
			
			console.log(response.data)
			console.log(response.status)
			$scope.error=response.data

/*			if($scope.error.code==1) 	// exception
				$scope.exception=response.data
			if($scope.error.code==2)
				$scope.duplicateUser=response.data
			if($scope.error.code==3)
				$scope.duplicateEmail=response.data */
			
			$location.path('/register')
		})
	
	}
	
		$scope.login=function()
		{
			console.log($scope.userObj)
			UserService.login($scope.userObj).then(function(response){
				$rootScope.currentUser=response.data
				$cookieStore.put('userDetails',response.data)
				$location.path('/home')
					
			},function(response){
				$scope.error=response.data.message
				$location.path('/login')
			})
		}
		
	$scope.updateUser=function() {
		UserService.updateUser($scope.user).then(function(response){
			alert('Update the details succcessfully')
			$location.path('/home')
		},function(response){
			if(response.status==401){
				$location.path('/login')
			}
			else{
			$scope.error=response.data
			$location.path('/editprofile')
			}
		})
	}
		
	if($rootScope.currentUser!=undefined){
	UserService.getUser().then(function(response){
		$scope.user=response.data //data is ntg but user object in json format 
	},function(response){
		console.log(response.status)
	})
	
	}
})
