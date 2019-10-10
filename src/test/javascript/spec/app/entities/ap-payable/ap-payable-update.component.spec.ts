import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayableUpdateComponent } from 'app/entities/ap-payable/ap-payable-update.component';
import { ApPayableService } from 'app/entities/ap-payable/ap-payable.service';
import { ApPayable } from 'app/shared/model/ap-payable.model';

describe('Component Tests', () => {
  describe('ApPayable Management Update Component', () => {
    let comp: ApPayableUpdateComponent;
    let fixture: ComponentFixture<ApPayableUpdateComponent>;
    let service: ApPayableService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayableUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ApPayableUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApPayableUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayableService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApPayable(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApPayable();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
