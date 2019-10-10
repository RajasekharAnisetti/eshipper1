import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { ApCategoryTypeUpdateComponent } from 'app/entities/ap-category-type/ap-category-type-update.component';
import { ApCategoryTypeService } from 'app/entities/ap-category-type/ap-category-type.service';
import { ApCategoryType } from 'app/shared/model/ap-category-type.model';

describe('Component Tests', () => {
  describe('ApCategoryType Management Update Component', () => {
    let comp: ApCategoryTypeUpdateComponent;
    let fixture: ComponentFixture<ApCategoryTypeUpdateComponent>;
    let service: ApCategoryTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApCategoryTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ApCategoryTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApCategoryTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApCategoryTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApCategoryType(123);
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
        const entity = new ApCategoryType();
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
