/**
 * 
 */
app.factory('FriendService',function($http){
	
	var friendService={}
	var BASE_URL="http://localhost:8089/Collaboration_middleware"
		
	friendService.listOfSuggestedUsers=function(){
		return $http.get(BASE_URL + "/getsuggestedusers")
	}
	friendService.sendFriendRequest=function(toId){
		return $http.get(BASE_URL + "/friendrequest/"+toId)
	}
	friendService.pendingRequests=function(){
		return $http.get(BASE_URL + "/pendingrequests")
	}
	
	friendService.updatePendingRequest=function(request){ //request is friend object (id,fromid,toid,status)
		return $http.put(BASE_URL + "/updatependingrequest",request) //request obj with updated status
	}
	
	friendService.listOfFriends=function(){
		return $http.get(BASE_URL + "/friendslist")
	}
	
	return friendService;
})