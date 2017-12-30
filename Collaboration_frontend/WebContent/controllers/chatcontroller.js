/**
 * 	
 */

app.controller('ChatController', function($rootScope,$scope,socket){
	alert('entering chat controller')
	$scope.chats=[];	//array of chat messages
	$scope.users=[];	//array of usernames
	$scope.stompClient=socket.stompClient
	
	$scope.$on('sockConnected',function(event,frame){
		
		alert('sockconnected')
		$scope.userName=$rootScope.currentUser.username
		// data is from client to server
		//data is username who joins the chatroom
		// newly joined user
		$scope.stompClient.subscribe("/app/join/"+$scope.userName,function(message){
			//message.body is list of usernames as string
			//convertion string to json 
			$scope.users=JSON.parse(message.body)	//list of users
			console.log($scope.users)
			$scope.$apply();
		})
		
		
		$scope.stompClient.subscribe("/topic/join",function(message){
				user=JSON.parse(message.body) 
				// for newly joined user , if statement will not satisfied and so statements inside
				//if will not get executed
				if(user != $scope.userName && $.inArray(user, $scope.users) == -1) {
					$scope.addUser(user);
					$scope.latestUser = user;
					$scope.$apply();
					$('#joinedChat').fadeIn(100).delay(10000).fadeOut(200);
				}
				
				
				
		})
	})
	
	 $scope.addUser = function(user) {
        $scope.users.push(user);
        $scope.$apply();
    };
    
    $scope.capitalize = function(str) {
        return str.charAt(0).toUpperCase() + str.slice(1);
    };
    
    $scope.sendMessage = function(chat) {
        chat.from = $scope.userName;
      
        $scope.stompClient.send("/app/chat", {}, JSON.stringify(chat));
        $rootScope.$broadcast('sendingChat', chat);
        $scope.chat.message = '';

    };
    
    $scope.$on('sockConnected', function(event, frame){
    	$scope.userName=$rootScope.currentUser.username;
    	
    	$scope.user=$rootScope.currentUser.username;
    	
    	$scope.stompClient.subscribe("/queue/chats/"+$scope.userName, function(message){
    		console.log("message for" +$scope.userName)
    		$scope.processIncomingMessage(message,false);
    	});
    	
    	//group chat
    	 $scope.stompClient.subscribe("/queue/chats", function(message) {
         	
             $scope.processIncomingMessage(message, true);
         });
    });
    
    
    // to add chat messages to the chat array $scope.chats=
    $scope.processIncomingMessage = function(message, isBroadcast) {
        message = JSON.parse(message.body);
        message.direction = 'incoming';
        
        if(message.from != $scope.userName) {
        	$scope.addChat(message);
            $scope.$apply(); // since inside subscribe closure
        }
    };
    
    $scope.addChat = function(chat) {
        $scope.chats.push(chat);
    };
    
    $scope.$on('sendingChat', function(event, sentChat) {
        chat = angular.copy(sentChat);
        chat.from = 'Me';
        chat.direction = 'outgoing';
        $scope.addChat(chat);
    });
	
})