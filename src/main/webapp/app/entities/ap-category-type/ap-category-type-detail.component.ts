import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApCategoryType } from 'app/shared/model/ap-category-type.model';

@Component({
  selector: 'jhi-ap-category-type-detail',
  templateUrl: './ap-category-type-detail.component.html'
})
export class ApCategoryTypeDetailComponent implements OnInit {
  apCategoryType: IApCategoryType;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ apCategoryType }) => {
      this.apCategoryType = apCategoryType;
    });
  }

  previousState() {
    window.history.back();
  }
}
