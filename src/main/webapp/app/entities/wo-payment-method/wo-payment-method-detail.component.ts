import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoPaymentMethod } from 'app/shared/model/wo-payment-method.model';

@Component({
  selector: 'jhi-wo-payment-method-detail',
  templateUrl: './wo-payment-method-detail.component.html'
})
export class WoPaymentMethodDetailComponent implements OnInit {
  woPaymentMethod: IWoPaymentMethod;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woPaymentMethod }) => {
      this.woPaymentMethod = woPaymentMethod;
    });
  }

  previousState() {
    window.history.back();
  }
}
