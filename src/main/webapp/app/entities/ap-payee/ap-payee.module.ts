import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Eshipper1SharedModule } from 'app/shared/shared.module';
import { ApPayeeComponent } from './ap-payee.component';
import { ApPayeeDetailComponent } from './ap-payee-detail.component';
import { ApPayeeUpdateComponent } from './ap-payee-update.component';
import { ApPayeeDeletePopupComponent, ApPayeeDeleteDialogComponent } from './ap-payee-delete-dialog.component';
import { apPayeeRoute, apPayeePopupRoute } from './ap-payee.route';

const ENTITY_STATES = [...apPayeeRoute, ...apPayeePopupRoute];

@NgModule({
  imports: [Eshipper1SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ApPayeeComponent,
    ApPayeeDetailComponent,
    ApPayeeUpdateComponent,
    ApPayeeDeleteDialogComponent,
    ApPayeeDeletePopupComponent
  ],
  entryComponents: [ApPayeeDeleteDialogComponent]
})
export class Eshipper1ApPayeeModule {}
