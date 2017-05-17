(function (ng) {
    var mod = ng.module("resenaModule");


    mod.controller("resenaCreateCtrl", ['$scope', '$http', '$stateParams', '$state',
        function ($scope, $http, $stateParams, $state) 
        {
            $scope.resena = {
                viajero: {
                    idUsuario: $stateParams.viajeroId
                },
                habitacion: {
                    id: $stateParams.habitacionId
                }
            };
            
            $scope.crearResena = function () {
                
                $http.post('api/resenas', $scope.resena)
                    .then(function (data) {
                        $state.go('viajeroDetail',{viajeroId: $stateParams.viajeroId});
                        console.log(data);
                    });
            };
        }]
    );
    
    mod.controller("resenaUpdateCtrl", ['$scope', '$http', '$stateParams', '$state',
        function ($scope, $http, $stateParams, $state) 
        {
            
            $scope.updateResena = function(){
                
                $http.put('api/resenas/'+ $stateParams.resenaId, $scope.currentResena)
                    .then(function(data){
                        $state.go('resenaDetailViajero',{resenaId: $stateParams.resenaId});
                        console.log(data);
                    });
            };
            
            
            $scope.currentStar = 0;

            $scope.overStar = function (s) {
                $scope.resetStars();
                for (var i = 1; i <= s; i++) {
                  document.getElementById('star' + i).style.color = '#dcb40a';
                }
                 console.log('overStar');
            };
                  
            $scope.leaveStar = function (s) {
                for (var i = s; i > 0; i--) {
                  document.getElementById('star' + i).style.color = '#212121';
                }
                console.log('leaveStar');
            };
            $scope.setDefaultStar = function (s) {
                $scope.currentStar = s;
                $scope.showSelectedStar();
                console.log('setDefaultStar');
            };
            $scope.showSelectedStar = function () {
                if ($scope.currentStar !== 0) {
                  $scope.overStar($scope.currentStar);
                }
                console.log('showSelectedStar');
            };
            $scope.resetStars = function () {
                for (var i = 5; i > 0; i--) {
                  document.getElementById('star' + i).style.color = '#212121';
                }
                console.log('resetStars');
            };
        }]
    );
    
    mod.controller("resenaViajeroListCtrl", ['$scope', '$http', '$stateParams', 'resenas',
        function ($scope, $http, $stateParams, resenas)
        {
            $scope.RecordsResena = resenas.data;
            
            $scope.filtrar = function(){
                
                var min = document.getElementById("listVRMin").value;
                var max = document.getElementById("listVRMax").value;
                
                $http.get('api/resenas/porViajero/'+$stateParams.viajeroId + '/'
                            + min + '/'
                            + max)
                    .then(function(lista){
                        
                        $scope.RecordsResena = lista.data;
                });
            };
        }]);
    
    mod.controller("resenaDetailViajeroCtrl", ['$scope', 'currentResena', '$http', '$stateParams', '$state',
        function ($scope, currentResena, $http, $stateParams, $state) 
        {
            $scope.currentResena = currentResena.data; 
            
            $scope.deleteResena = function(){
                
                $http.delete('api/resenas/'+ $stateParams.resenaId)
                    .then(function(data){
                        $state.go('viajeroDetail',{viajeroId: $stateParams.viajeroId});
                        console.log(data);
                    });
            };

        }]
    );
    
})(window.angular);