package info.keidi.usecases;

import info.keidi.ports.ClientRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
abstract class TestBaseClass {
  @Mock protected ClientRepository clientRepository;
}
