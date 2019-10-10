import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApPayeeType } from 'app/shared/model/ap-payee-type.model';

@Component({
  selector: 'jhi-ap-payee-type-detail',
  templateUrl: './ap-payee-type-detail.component.html'
})
export class ApPayeeTypeDetailComponent implements OnInit {
  apPayeeType: IApPayeeType;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ apPayeeType }) => {
      this.apPayeeType = apPayeeType;
    });
  }

  previousState() {
    window.history.back();
  }
}
