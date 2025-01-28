/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

--  Good entry, not initialized
INSERT INTO datastore_entity (id, status)
VALUES ('00000000-0000-0000-0000-000000000000', 'SETUP');
--  Good entry, initialized did:tdw
INSERT INTO datastore_entity (id, status)
VALUES ('00000000-0000-0000-0000-000000000001', 'ACTIVE');
INSERT INTO vc_entity (base_id, vc_type, raw_vc, vc_payload, read_uri)
VALUES ('00000000-0000-0000-0000-000000000001',
        'TokenStatusListJWT',
        'TEST_READ',
        '{"test":"test"}',
        'http://test.url');

--  Disabled entry
INSERT INTO datastore_entity (id, status)
VALUES ('00000000-0000-0000-0000-000000000004', 'DISABLED');

--  Deactivated entry
INSERT INTO datastore_entity (id, status)
VALUES ('00000000-0000-0000-0000-000000000005', 'DEACTIVATED');
