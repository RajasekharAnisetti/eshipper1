import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Eshipper1SharedModule } from 'app/shared/shared.module';
import { WoPaymentMethodComponent } from './wo-payment-method.component';
import { WoPaymentMethodDetailComponent } from './wo-payment-method-detail.component';
import { WoPaymentMethodUpdateComponent } from './wo-payment-method-update.component';
import { WoPaymentMethodDeletePopupComponent, WoPaymentMethodDeleteDialogComponent } from './wo-payment-method-delete-dialog.component';
import { woPaymentMethodRoute, woPaymentMethodPopupRoute } from './wo-payment-method.route';

const ENTITY_STATES = [...woPaymentMethodRoute, ...woPaymentMethodPopupRoute];

@NgModule({
  imports: [Eshipper1SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WoPaymentMethodComponent,
    WoPaymentMethodDetailComponent,
    WoPaymentMethodUpdateComponent,
    WoPaymentMethodDeleteDialogComponent,
    WoPaymentMethodDeletePopupComponent
  ],
  entryComponents: [WoPaymentMethodDeleteDialogComponent]
})
export class Eshipper1WoPaymentMethodModule {}
