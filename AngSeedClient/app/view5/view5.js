'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'view5/view5.html',
                    controller: 'View5Ctrl'
                });
            }])


        .controller('View5Ctrl', function ($http, $scope) {
            
            $scope.ifAdmin = false;
            
              $http.get('api/demoadmin')
            .success(function (data, status, headers, config) {
              $scope.data = data;
              
              $scope.msg = "Du er nu logget ind som ADMIN";
              $scope.ifAdmin = true;
              
            })
            .error(function (data, status, headers, config) {
              
             });


            /**:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
                GET ALL USER FROM DATABASE VIA REST API
            ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**/
            $scope.getUsers = function () {
                $http.get('api/demoadmin/users')
                        
                        .success(function (data, status, headers, config) {
                            
                                    $scope.data = data;

                        })
                        .error(function (data, status, headers, config) {

                        });
            };

            /**:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
                DELETE SELECTED USER FROM DATABASE
            ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**/
            $scope.deleteUser = function (username) {

                // CONFIRM MESSAGE
                var answer = confirm("Are you sure you want to delete this user?");

                // IF CONFIRM THEN DELETE USER FROM DATABASE.
                if (answer) {

                    $http.delete('api/demoadmin/user/' + username)
                            .success(function (data, status, headers) {
                                
                                // GET ALL USER AGAIN. UPDATE USERS TABLE AFTER DELETING.
                                $scope.getUsers();

                            })
                            .error(function (data, status, header, config) {
                                
                                // IF ERROR, USER GET A MESSAGE
                                alert("Error! The user was not deleted from database, please try again later.");
                                
                            });

                }
            };

        });