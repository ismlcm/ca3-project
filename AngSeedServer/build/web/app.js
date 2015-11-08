'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'ngAnimate',
    'ui.bootstrap',
    'myApp.security',
    'myApp.view1',
    'myApp.view2',
    'myApp.view3',
    'myApp.view4',
    'myApp.view5',
    'myApp.filters',
    'myApp.directives',
    'myApp.factories',
    'myApp.services'
]).
        config(['$routeProvider', function ($routeProvider) {
                $routeProvider.otherwise({redirectTo: '/view1'});
            }]).
        config(function ($httpProvider) {
            $httpProvider.interceptors.push('authInterceptor');
        }).
                
                
        /**:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
            SIGN UP MODAL AND LOGIN START
         ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**/

        controller('ModalDemoCtrl', function ($scope, $uibModal, $log, $http) {
            
            $scope.addUserAsJSON = function () {
                // Writing it to the server
                //		
                var dataObj = {
                    userName: $scope.userName,
                    password: $scope.password
                };
                
                // POST SIGNUP
                var res = $http.post('/api/newuser/adduser', dataObj);
                res.success(function (data, status, headers, config) {
                    
                    // IF SIGN UP SUCCESS, USER GET A POPUP MESSAGE.
                    alert("User successfully created");
                    
                    
                });
                res.error(function (data, status, headers, config) {
                    
                    // IF SIGN UP ERROR, USER GET A POPUP MESSAGE.
                    alert("failure message: " + JSON.stringify({data: data}));
                });
                
                // RESET FIELDS, MAKE THEM EMPTY AGAIN.
                $scope.userName = '';
                $scope.password = '';
            };

            /**:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
                SIGN UP MODAL AND LOGIN END
            ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**/



            /**:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
                SIGN UP MODAL WINDOW START
            ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**/
            $scope.items = ['item1', 'item2', 'item3'];

            $scope.animationsEnabled = true;

            $scope.open = function (size) {

                var modalInstance = $uibModal.open({
                    animation: $scope.animationsEnabled,
                    templateUrl: 'signupmodal.html',
                    controller: 'ModalInstanceCtrl',
                    size: size,
                    resolve: {
                        items: function () {
                            return $scope.items;
                        }

                    }
                });

                modalInstance.result.then(function (selectedItem) {
                    $scope.selected = selectedItem;
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            };

            $scope.toggleAnimation = function () {
                $scope.animationsEnabled = !$scope.animationsEnabled;
            };

        })

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

        .controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {
            
            $scope.items = items;
            $scope.selected = {
                item: $scope.items[0]
            };

            $scope.ok = function () {
                $uibModalInstance.close($scope.selected.item);
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
            
        });
        
        /**:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
            SIGN UP MODAL WINDOW END
         ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**/