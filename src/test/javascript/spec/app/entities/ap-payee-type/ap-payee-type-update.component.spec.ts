import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayeeTypeUpdateComponent } from 'app/entities/ap-payee-type/ap-payee-type-update.component';
import { ApPayeeTypeService } from 'app/entities/ap-payee-type/ap-payee-type.service';
import { ApPayeeType } from 'app/shared/model/ap-payee-type.model';

describe('Component Tests', () => {
  describe('ApPayeeType Management Update Component', () => {
    let comp: ApPayeeTypeUpdateComponent;
    let fixture: ComponentFixture<ApPayeeTypeUpdateComponent>;
    let service: ApPayeeTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayeeTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ApPayeeTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApPayeeTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayeeTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApPayeeType(123);
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
        const entity = new ApPayeeType();
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
