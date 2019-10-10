import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Eshipper1SharedModule } from 'app/shared/shared.module';
import { ApPayableCreditNotesTransComponent } from './ap-payable-credit-notes-trans.component';
import { ApPayableCreditNotesTransDetailComponent } from './ap-payable-credit-notes-trans-detail.component';
import { ApPayableCreditNotesTransUpdateComponent } from './ap-payable-credit-notes-trans-update.component';
import {
  ApPayableCreditNotesTransDeletePopupComponent,
  ApPayableCreditNotesTransDeleteDialogComponent
} from './ap-payable-credit-notes-trans-delete-dialog.component';
import { apPayableCreditNotesTransRoute, apPayableCreditNotesTransPopupRoute } from './ap-payable-credit-notes-trans.route';

const ENTITY_STATES = [...apPayableCreditNotesTransRoute, ...apPayableCreditNotesTransPopupRoute];

@NgModule({
  imports: [Eshipper1SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ApPayableCreditNotesTransComponent,
    ApPayableCreditNotesTransDetailComponent,
    ApPayableCreditNotesTransUpdateComponent,
    ApPayableCreditNotesTransDeleteDialogComponent,
    ApPayableCreditNotesTransDeletePopupComponent
  ],
  entryComponents: [ApPayableCreditNotesTransDeleteDialogComponent]
})
export class Eshipper1ApPayableCreditNotesTransModule {}
