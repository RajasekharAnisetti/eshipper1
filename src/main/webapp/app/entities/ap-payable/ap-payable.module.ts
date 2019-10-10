import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Eshipper1SharedModule } from 'app/shared/shared.module';
import { ApPayableComponent } from './ap-payable.component';
import { ApPayableDetailComponent } from './ap-payable-detail.component';
import { ApPayableUpdateComponent } from './ap-payable-update.component';
import { ApPayableDeletePopupComponent, ApPayableDeleteDialogComponent } from './ap-payable-delete-dialog.component';
import { apPayableRoute, apPayablePopupRoute } from './ap-payable.route';

const ENTITY_STATES = [...apPayableRoute, ...apPayablePopupRoute];

@NgModule({
  imports: [Eshipper1SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ApPayableComponent,
    ApPayableDetailComponent,
    ApPayableUpdateComponent,
    ApPayableDeleteDialogComponent,
    ApPayableDeletePopupComponent
  ],
  entryComponents: [ApPayableDeleteDialogComponent]
})
export class Eshipper1ApPayableModule {}
