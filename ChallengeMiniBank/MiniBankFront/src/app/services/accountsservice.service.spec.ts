import { TestBed } from '@angular/core/testing';

import { AccountsserviceService } from './accountsservice.service';

describe('AccountsserviceService', () => {
  let service: AccountsserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountsserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
