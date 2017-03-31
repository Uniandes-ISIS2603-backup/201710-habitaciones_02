(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        // Internal modules dependencies       
        'anfitrionModule',
        'viajeroModule',

        'habitacionModule',
        'disponibilidadModule',
        'resenaModule',
        'reservaModule'

        
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
