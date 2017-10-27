'use strict';

define(['frontend'], function(frontend) {
    frontend.config(function(MandrillProvider) {
      MandrillProvider.setApiKey('ywbgzuU_RFuYgILg85NuGw');
      console.log('this shit is being done');
    });
});
