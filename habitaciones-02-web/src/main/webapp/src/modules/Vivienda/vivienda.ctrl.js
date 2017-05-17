(function (ng) {
    var mod = ng.module("viviendaModule");
    mod.constant("viviendasContext", "api/viviendas");

    mod.controller("viviendaCrearCtrl", ['$scope', '$http', 'viviendasContext',
        function ($scope, $http, viviendasContext) {
            $scope.vivienda = {};
            $scope.crear = function () {
                $http.post(viviendasContext, $scope.vivienda)
                .then(function (data) {
                    console.log(data);
                });
            };
        }]);
    mod.controller("viviendaListCtrl", ['$scope', 'viviendas',
        function ($scope, viviendas) {
            $scope.viviendasRecords = viviendas.data;
        }
    ]);

})(window.angular);