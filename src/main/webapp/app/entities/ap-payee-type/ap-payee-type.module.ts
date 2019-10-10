import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Eshipper1SharedModule } from 'app/shared/shared.module';
import { ApPayeeTypeComponent } from './ap-payee-type.component';
import { ApPayeeTypeDetailComponent } from './ap-payee-type-detail.component';
import { ApPayeeTypeUpdateComponent } from './ap-payee-type-update.component';
import { ApPayeeTypeDeletePopupComponent, ApPayeeTypeDeleteDialogComponent } from './ap-payee-type-delete-dialog.component';
import { apPayeeTypeRoute, apPayeeTypePopupRoute } from './ap-payee-type.route';

const ENTITY_STATES = [...apPayeeTypeRoute, ...apPayeeTypePopupRoute];

@NgModule({
  imports: [Eshipper1SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ApPayeeTypeComponent,
    ApPayeeTypeDetailComponent,
    ApPayeeTypeUpdateComponent,
    ApPayeeTypeDeleteDialogComponent,
    ApPayeeTypeDeletePopupComponent
  ],
  entryComponents: [ApPayeeTypeDeleteDialogComponent]
})
export class Eshipper1ApPayeeTypeModule {}
