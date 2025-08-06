package com.example.synchronisation;

import java.util.List;

public interface ObjectSnapshotService {

    List<String> snapshot(List<ObjectIdWrapper> objectIdentifiers);
}
