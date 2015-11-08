'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'view4/view4.html',
                    controller: 'View4Ctrl'
                });
            }])


        .controller('View4Ctrl', function ($http, $scope) {
            
            $scope.ifUser = false;
            
            $http.get('api/demouser')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;

                        $scope.msg = "Du er nu logget ind som USER";
                        
                        $scope.ifUser = true;

                    })
                    .error(function (data, status, headers, config) {

                    });


            /**:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
             GET CURRENCY
             ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**/
            $scope.getCurrency = function () {
                $http.get('api/currency/all')

                        .success(function (data, status, headers, config) {

                            $scope.data = data;
                    
                    console.log($scope.data);

                        })
                        .error(function (data, status, headers, config) {

                        });
            };
            
            $scope.calCurrency = function () {
                
                $http.get('api/currency/cal/' + $scope.curAmount + '/' + $scope.curFrom + '/' + $scope.curTo)

                        .success(function (data, status, headers, config) {

                            $scope.calculate = data;
                    
                            $scope.parseCal = parseFloat($scope.calculate).toFixed(2);
                    
                    console.log($scope.calculate);

                        })
                        .error(function (data, status, headers, config) {

                        });
            };

        });


