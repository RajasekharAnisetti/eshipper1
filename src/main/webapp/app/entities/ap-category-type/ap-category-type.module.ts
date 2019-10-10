import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Eshipper1SharedModule } from 'app/shared/shared.module';
import { ApCategoryTypeComponent } from './ap-category-type.component';
import { ApCategoryTypeDetailComponent } from './ap-category-type-detail.component';
import { ApCategoryTypeUpdateComponent } from './ap-category-type-update.component';
import { ApCategoryTypeDeletePopupComponent, ApCategoryTypeDeleteDialogComponent } from './ap-category-type-delete-dialog.component';
import { apCategoryTypeRoute, apCategoryTypePopupRoute } from './ap-category-type.route';

const ENTITY_STATES = [...apCategoryTypeRoute, ...apCategoryTypePopupRoute];

@NgModule({
  imports: [Eshipper1SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ApCategoryTypeComponent,
    ApCategoryTypeDetailComponent,
    ApCategoryTypeUpdateComponent,
    ApCategoryTypeDeleteDialogComponent,
    ApCategoryTypeDeletePopupComponent
  ],
  entryComponents: [ApCategoryTypeDeleteDialogComponent]
})
export class Eshipper1ApCategoryTypeModule {}
