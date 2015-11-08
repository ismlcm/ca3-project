'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider',  function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'view3/view3.html',
                    controller: 'View3Ctrl'
                });  
            }])

//.controller('View3Ctrl', function($http,$scope) {
//  $http.get('api/demouser')
//            .success(function (data, status, headers, config) {
//              $scope.data = data;
//              
//              $scope.msg = "Du er nu logget ind som USER";
//              
//            })
//            .error(function (data, status, headers, config) {
//              
//             });
// 
//})

        .controller("View3Ctrl", ["$http", "$scope", function ($http, $scope) {

                 $scope.ifUser = true;
        
                  $http.get('api/demouser')
                    .success(function (data, status, headers, config) {
                    $scope.data = data;
              
                    $scope.msg = "Du er nu logget ind som USER";
                    $scope.ifUser = true;
              
                  })
                    .error(function (data, status, headers, config) {
              
                 });


                // HIDE ALL
                 $scope.show = false;


                /**:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
                 SEARCH COMPANY START
                 ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**/
                $scope.searchNow = function () {

                    // START SEARCH WHEN THE INPUT LENGTH IS 8. 
                    if ($scope.searchvat.length === 8) {

                        $http({
                            method: 'GET',
                            url: "http://cvrapi.dk/api?vat=" + $scope.searchvat + "&country=dk",
                            headers: {
                                'Content-Type': 'application/json',
                                'Accept': 'application/json, text/plain, * / *'
                            }
                        }).then(function successCallback(response) {

                            $scope.size = response.data.length;

                            var company = [{}];
                            $scope.company = response.data;

                            $scope.depSize = $scope.company.productionunits.length;

                            // IF COMPANY EXIST THEN SHOW CONTENT
                             $scope.show = true;


                        }, function errorCallback(response) {

                            // IF COMPANY NOT EXIST THEN PRINT A ERROR TEXT UNDER INPUT FIELD.
                            $scope.cvrerror = "The company(" + $scope.searchvat + ") does not exist!";
                        });

                    }
                    else
                    {
                        // IF INPUT LENGTH UNDER OR OVER 8, THEN REMOVE ERROR TEXT AND HIDE ALL CONTENT.
                        $scope.cvrerror = "";
                        $scope.show = false;
                    }

                };
                /**:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
                 SEARCH COMPANY END
                 ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**/

                /**:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
                 BOOTSTRAP ANGULAR ACCORDING EFFECT ENABLE
                 ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**/
                $scope.oneAtATime = true;

                $scope.items = ['Item 1', 'Item 2', 'Item 3'];

                $scope.addItem = function () {
                    var newItemNo = $scope.items.length + 1;
                    $scope.items.push('Item ' + newItemNo);
                };

                $scope.status = {
                    isFirstOpen: true,
                    isFirstDisabled: false
                };


            }]);